package lvchanglong

import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File;
import java.util.Calendar;

import javax.imageio.ImageIO

/**
 * 帮助工具
 * @author 吕常龙
 *
 */
class Helper {

	/**
	 * 年
	 */
	static def getNian() {
		Calendar calendar = Calendar.getInstance()
		return calendar.get(Calendar.YEAR)
	}
	
	/**
	 * 月
	 */
	static def getYue() {
		Calendar calendar = Calendar.getInstance()
		return calendar.get(Calendar.MONTH)
	}
	
	/**
	 * 日
	 */
	static def getRi() {
		Calendar calendar = Calendar.getInstance()
		return calendar.get(Calendar.DAY_OF_MONTH)
	}
	
	/**
	 * 星期
	 */
	static def getXingQi() {
		Calendar calendar = Calendar.getInstance()
		switch(calendar.get(Calendar.DAY_OF_WEEK)) {
			case 1://星期天
				return "星期日"//日
			case 2://星期一
				return "星期一"//月
			case 3://星期二
				return "星期二"//火
			case 4://星期三
				return "星期三"//水
			case 5://星期四
				return "星期四"//木
			case 6://星期五
				return "星期五"//金
			case 7://星期六
				return "星期六"//土
		}
	}
	
	/**
	 * 图片压缩
	 * @param fileSrc 原始图片
	 * @param fileTar 目标图片
	 * @param widthTar 目标宽度（"0":自适应）
	 * @param heightTar 目标高度（"0":自适应）
	 */
	static def yaSuo(File fileSrc, File fileTar, Integer widthTar, Integer heightTar) {
		if(fileSrc.exists()) {
			BufferedImage biSrc = ImageIO.read(fileSrc)
			Integer widthSrc = biSrc.getWidth()//原图宽度
			Integer heightSrc = biSrc.getHeight()//原图高度
			
			if(widthTar == 0) {//宽度自适应(高度固定)
				def rate = heightSrc / heightTar
				widthTar = widthSrc / rate
			} else if (heightTar == 0) {//高度自适应(宽度自适)
				def rate = widthSrc / widthTar
				heightTar = heightSrc / rate
			}
			
			Image scaledImage = biSrc.getScaledInstance(widthTar, heightTar, Image.SCALE_SMOOTH)//原图压缩
			BufferedImage biTar = new BufferedImage(widthTar, heightTar, BufferedImage.TYPE_INT_RGB)
			biTar.getGraphics().drawImage(scaledImage, 0, 0, null)

			ImageIO.write(biTar, this.getFileType(fileTar.getName()), fileTar)
		}
	}
	
	/**
	 * 图片压缩(自身减肥)
	 * @param file
	 * @param widthTar 目标宽度（0/自适应）
	 * @param heightTar 目标高度（0/自适应）
	 */
	static def yaSuo(File file, Integer widthTar, Integer heightTar) {
		this.yaSuo(file, file, widthTar, heightTar)
	}

	/**
	 * 随机获取
	 * @param array
	 * @return
     */
	static def random(def array) {
		if(array) {
			def sz = array.size()
			if(sz == 1) {
				return array[0]
			}
			Random random = new Random(System.currentTimeMillis())
			int bound = array.size()-1//设定范围
			int idx = random.nextInt(bound)
			return array[idx]
		}
		return null
	}
	
	static main(args) {
//		println "随机获取：" + this.random(["H", "E", "L", "L", "O"])
//		println "生肖属相：" + this.getShengXiao(1988)
//		println "宽度800，高度自适：" + this.yaSuo(new File("C:\\Users\\lvchanglong\\Desktop\\素材\\左手.jpg"), 800, 0)
//		println "文件名：" + this.getFile("C:\\Users\\admin\\Desktop\\新建文本文档.txt").getName()
//		println "星期几：" + this.getXingQi()
	}

}
