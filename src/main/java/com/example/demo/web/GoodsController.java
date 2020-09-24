package com.example.demo.web;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.example.demo.pojo.Goods;
import com.example.demo.pojo.ShoppingCar;
import com.example.demo.pojo.Student;
import com.example.demo.service.ICardService;
import com.example.demo.util.AlipayConfig;

import io.minio.MinioClient;

@RestController
@RequestMapping("/goods")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*") // 设置返回自动组装成json格式
public class GoodsController {
	@Autowired
	private ICardService cardService;

	@RequestMapping(path = "/showshoppingcar", method = { RequestMethod.GET, RequestMethod.POST })
	public List<ShoppingCar> showAllshopping(HttpSession session) {
		Student stu = (Student) session.getAttribute("stu");

		return cardService.listAllGoods(stu.getStuId());
	}

	@RequestMapping(path = "/addshoppingcar", method = { RequestMethod.GET, RequestMethod.POST })
	public Boolean addShoppingCar(ShoppingCar shoppingCar, HttpSession session) {
//		System.out.println(shoppingCar);
		Student stu = (Student) session.getAttribute("stu");
//		System.out.println(stu);
		shoppingCar.setStuId(stu.getStuId());
		return cardService.addShoppingCar(shoppingCar);
	}

	@RequestMapping(path = "/listgoods", method = { RequestMethod.GET, RequestMethod.POST })
	public List<Goods> showAllGoods() {

		return cardService.listAllGoods();
	}

	@RequestMapping(path = "/querygoods", method = { RequestMethod.GET, RequestMethod.POST })
	public Goods queryGoods(Integer goodsId) {
		return cardService.selectGoods(goodsId);
	}

	@RequestMapping(path = "/savegoods", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean updateGoods(Goods goods, MultipartFile file) {
		boolean rt = false;
		if (file != null) {
			try {
				MinioClient minioClient = new MinioClient("http://127.0.0.1:9000", "minioadmin", "minioadmin"); // 连接
				if (!minioClient.bucketExists("goods")) { // 是否存在名为“test”的bucket
					minioClient.makeBucket("goods");
				}
				String fileName = file.getOriginalFilename();
				String newName = UUID.randomUUID().toString().replaceAll("-", "")
						+ fileName.substring(fileName.lastIndexOf("."));
				InputStream inputStream = file.getInputStream(); // 获取file的inputStream
				
				minioClient.putObject("goods", newName, inputStream, "image/png"); // 上传
				
				inputStream.close();
				String url = minioClient.getObjectUrl("goods", newName); // 文件访问路径

				goods.setGoodsImg(url);
				rt= cardService.updateGoods(goods);
			} catch (Exception e) {
				e.printStackTrace();

			}
		}else {
			rt= cardService.updateGoods(goods);
		}
		return rt;

	}

	@RequestMapping(path = "/addgoods", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean addGoods(Goods goods, MultipartFile file) {
		if (file.isEmpty() || file.getSize() == 0) {
			return false;
		}
		try {
			MinioClient minioClient = new MinioClient("http://127.0.0.1:9000", "minioadmin", "minioadmin"); // 连接
			if (!minioClient.bucketExists("goods")) { // 是否存在名为“test”的bucket
				minioClient.makeBucket("goods");
			}
			String fileName = file.getOriginalFilename();
			String newName = UUID.randomUUID().toString().replaceAll("-", "")
					+ fileName.substring(fileName.lastIndexOf("."));
			InputStream inputStream = file.getInputStream(); // 获取file的inputStream
			// image/png
			minioClient.putObject("goods", newName, inputStream, "image/png"); // 上传
			// minioClient.putObject("goods", newName, inputStream,
			// "application/octet-stream"); // 上传
			inputStream.close();
			String url = minioClient.getObjectUrl("goods", newName); // 文件访问路径

			goods.setGoodsImg(url);
			return cardService.addGoods(goods);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cardService.addGoods(goods);
	}

	@RequestMapping(path = "/delgoods", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean delGoods(Integer goodsId) {
		return cardService.deleteGoods(goodsId);
	}

	@RequestMapping(path = "/delshopgoods", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean delShopGoods(Integer carId) {
//		System.out.println(carId);
		return cardService.deleteshopping(carId);
	}

	@RequestMapping(path = "/delAllGoods", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean delAllGoods(Integer[] id) {
		boolean rt = false;
		for (Integer goodsId : id) {
			rt = cardService.deleteGoods(goodsId);
			if (!rt) {
				rt = false;
			}
		}
		return rt;
	}

	@RequestMapping(path = "/delAllShopGoods", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean delAllShoppingGoods(Integer[] id) {
		boolean rt = false;
		for (Integer carId : id) {
			rt = cardService.deleteshopping(carId);
			if (!rt) {
				rt = false;
			}
		}
		return rt;
	}

	@RequestMapping(path = "/querygoodsname", method = { RequestMethod.GET, RequestMethod.POST })
	public List<Goods> queryGoods(String goodsName) {
		return cardService.queryGoodsByName(goodsName);
	}

	@RequestMapping(path = "/pay", method = { RequestMethod.GET, RequestMethod.POST })
	public String pay(Integer[] id, Integer allmoney, HttpServletRequest request) {

		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				AlipayConfig.sign_type);
		String result = null;
		// 设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		// 商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = UUID.randomUUID().toString() + "";
		// 付款金额，必填
		String total_amount = allmoney + "";
		// 订单名称，必填
		String subject = UUID.randomUUID().toString() + "";
		// 商品描述，可空
		String body = null;

		alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
				+ "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		try {
			result = alipayClient.pageExecute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Integer carId : id) {
			cardService.deleteshopping(carId);
		}

		return result;
	}

	@RequestMapping(path = "/querygoodsbyId", method = { RequestMethod.GET, RequestMethod.POST })
	public Goods queryGoodsById(Integer goodsId) {
//		System.out.println(goodsId);
		return cardService.selectGoodsById(goodsId);
	}

//	
}
