package common

class CommonHelper {

    static String getRealIp(def request) {
        def realIp = request.getHeader("X-Real-Ip")
        if (realIp == null) {
            realIp = request.getRemoteAddr()
        }
        return realIp
    }

    /**
     * 是否是Linux操作系统
     */
    static boolean isLinux() {
        return System.getProperty("os.name").equalsIgnoreCase("Linux")
    }

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
		println "星期几：" + this.getXingQi()
    }

}
