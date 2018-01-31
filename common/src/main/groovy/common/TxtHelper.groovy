package common

class TxtHelper {

	/**
	 * txt文档加载（read text file, auto recognize bom marker or use system default if markers not found.）
	 */
	private static char[] load(File file) throws IOException {
		BufferedReader reader = null;
		CharArrayWriter writer = null;
		UnicodeReader r = new UnicodeReader(file.newInputStream(), null);

		char[] buffer = new char[16 * 1024];   // 16k buffer
		int read;
		try {
			reader = new BufferedReader(r);
			writer = new CharArrayWriter();
			while( (read = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, read);
			}
			writer.flush();
			return writer.toCharArray();
		} catch (IOException ex) {
			throw ex;
		} finally {
		   	try {
				writer.close();
				reader.close();
				r.close();
			} catch (Exception ex) {

		    }
		}
	}

	/**
	 * txt文档重写（为txt文档增加UTF8 BOM mark）
	 */
	private static void rewrite(File targetFile, char[] data) throws IOException {
		BufferedWriter bw = null;
		OutputStreamWriter osw = null;

		def fos = targetFile.newOutputStream();
		try {
		    // write UTF8 BOM mark
		    if (targetFile.length() < 1) {
				byte[] bom = new byte[3];
				bom[0] = (byte)0xEF;
				bom[1] = (byte)0xBB;
				bom[2] = (byte)0xBF;
				fos.write(bom);
		    }

		    osw = new OutputStreamWriter(fos, "UTF-8");
		    bw = new BufferedWriter(osw);

			if (data != null) {
				bw.write(data);
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			try {
			   bw.close();
			   fos.close();
		    } catch (Exception ex) {

		    }
		}
	}

	/**
	 * 统一txt文档格式编码UTF8
     */
	static void resetBom(File file) {
		def chars = this.load(file) //txt文档加载
		this.rewrite(file, chars) //为原始txt文档增加UTF8 BOM mark
	}

	/**
	 * 按行获得文本内容
	 */
	static List<String> readLines(File file) throws IOException {
		this.resetBom(file) //增加UTF8 BOM mark
		UnicodeReader r = new UnicodeReader(file.newInputStream(), null);
		BufferedReader reader = new BufferedReader(r);
		return reader.readLines()
	}

	static main(args) {
		def file = new File("E:/", "术语表.txt")
		TxtHelper.readLines(file)
	}

}
