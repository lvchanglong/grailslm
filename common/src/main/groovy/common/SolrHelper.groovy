package common

import org.apache.solr.client.solrj.SolrClient
import org.apache.solr.client.solrj.impl.HttpSolrClient
import org.apache.solr.client.solrj.response.QueryResponse
import org.apache.solr.common.SolrDocument
import org.apache.solr.common.SolrDocumentList
import org.apache.solr.common.SolrInputDocument
import org.apache.solr.common.params.ModifiableSolrParams

/**
 * Created by lvchanglong on 2017/12/18.
 */
class SolrHelper {
    String root = null //安装根目录
    String solr = null //可执行文件

    /**
     * 是否是linux操作系统
     */
    static def isLinux() {
        return CommonHelper.isLinux()
    }

    /**
     * 操作系统名称 linux / windows
     */
    static String getOSName() {
        return this.isLinux()?"linux":"windows"
    }

    SolrHelper(String root = "") {
        if(!root) {//设置默认值
            def properties = new Properties()
            properties.load(this.class.getResourceAsStream("/config_${SolrHelper.getOSName()}.properties"))
            this.root = properties.getProperty("Solr")
        } else {
            this.root = root
        }

        if(SolrHelper.isLinux()) {//Linux
            this.solr = this.root + "/bin/solr"
        } else {//Windows
            this.solr = this.root + "/bin/solr.cmd"
        }

        this.status()
        this.restart()
        this.status()
    }

    def restart() {
        List<String> command = new ArrayList<String>()
        command.add(this.solr)
        command.add("restart")
        command.add("-p")
        command.add("8983")
        ProcessBuilder processBuilder= new ProcessBuilder(command)
        processBuilder.redirectErrorStream(true)
        Process process = processBuilder.start()
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))
        String line = null;
        while(null != (line = bufferedReader.readLine())) {
            //println "."
            println line
        }
        process.waitFor()
        bufferedReader.close()
        process.destroy()
    }

    def status() {
        List<String> command = new ArrayList<String>()
        command.add(this.solr)
        command.add("status")
        ProcessBuilder processBuilder= new ProcessBuilder(command)
        processBuilder.redirectErrorStream(true)
        Process process = processBuilder.start()
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))
        String line = null;
        while(null != (line = bufferedReader.readLine())) {
            //println "."
            println line
        }
        process.waitFor()
        bufferedReader.close()
        process.destroy()
    }

    def stop() {
        List<String> command = new ArrayList<String>()
        command.add(this.solr)
        command.add("stop -p 8983")
        ProcessBuilder processBuilder= new ProcessBuilder(command)
        processBuilder.redirectErrorStream(true)
        Process process = processBuilder.start()
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))
        String line = null;
        while(null != (line = bufferedReader.readLine())) {
            //println "."
            println line
        }
        process.waitFor()
        bufferedReader.close()
        process.destroy()
    }

    //-------------------------------------------------------------------------------------------------------------------

    /**
     * solr create -c core1 -p 8983
     * @param urlString
     * @return
     */
    static SolrClient getSolrClient(String urlString = "http://localhost:8983/solr/core1") {
        return new HttpSolrClient(urlString)
    }

    /**
     * 获得
     * @param solr
     * @param id
     * @return
     */
    static SolrDocument get(SolrClient solr, def id) {
        return solr.getById(id.toString())
    }

    /**
     * 检索
     * ModifiableSolrParams params = new ModifiableSolrParams()
     * params.add("rows", "1")
     * params.add("q", "username:*常*")
     * @param solr
     * @param params
     */
    static HashMap search(SolrClient solr, ModifiableSolrParams params) {
        QueryResponse response = solr.query(params)
        SolrDocumentList documdentList = response.getResults()
        return [instanceList:documdentList, instanceCount:documdentList.getNumFound()]
    }

    /**
     * 添加
     * @param solr
     * @param document
     * SolrInputDocument document = new SolrInputDocument()
     * document.addField("id", "core1")
     * document.addField("username", "吕常龙")
     * document.addField("password", "123456")
     */
    static def add(SolrClient solr, SolrInputDocument document) {
        solr.add(document)
        return solr.commit()
    }

    /**
     * 删除
     * @param solr
     * @param id
     */
    static def delete(SolrClient solr, def id) {
        solr.deleteById(id.toString())
        return solr.commit()
    }

    /**
     * 查询删除
     * @param solr
     * @param query "name:*常*"
     */
    static def deleteByQuery(SolrClient solr, String query) {
        solr.deleteByQuery(query)
        return solr.commit()
    }

    static main(args) {

        def run = new SolrHelper()
        run.status()
        run.restart()

        //--------------------------------------------------------------------------------------------------------------

        SolrClient solr = SolrHelper.getSolrClient()

//        SolrInputDocument document = new SolrInputDocument()
//        document.addField("k", "25.1")
//        document.addField("p", 3)
//        document.addField("title", "1.Applicability")
//        document.addField("content", "These methods and procedures are provided in the interest of certification approval for use during all transport category airplane flight test certification activities. This material is not to be construed as regulatory or having a mandatory effect. The procedures set forth herein are one acceptable means of compliance with applicable sections of part 25. Any alternative means proposed by the applicant should be given due consideration. Applicants are encouraged to use their technical ingenuity and resourcefulness in order to develop more efficient and less costly methods of complying with the requirements of part 25. Since these methods and procedures are only one acceptable means of compliance, individuals should be guided by the intent of the methods provided in this AC. As deviations from the methods and procedures described in this AC may occur, FAA certification personnel will coordinate what they consider to be major deviations with the Transport Standards Staff (ANM-110) of the Transport Airplane Directorate. If in their judgment, however, a deviation is considered to be minor, coordination with ANM- 110 may not be necessary.")
//        document.addField("zIndex", 0)
//        solr.add(document)
//        def updateResponse = solr.commit()

//        SolrHelper.deleteByQuery(solr, "k:25.1")

        ModifiableSolrParams solrParams = new ModifiableSolrParams()
        solrParams.add("start", "0")
        solrParams.add("rows", "10")
        solrParams.add("q", "k:测试* || title:测试* || objName:测试*")
        //solrParams.add("q", "*:*")

        /**
         * 高亮配置
         */
        solrParams.add("hl", "on")
        solrParams.add("hl.fl", "k,title,objName,content")
        solrParams.add("hl.snippets", "1")
        solrParams.add("hl.simple.pre", "<font color='red'>")
        solrParams.add("hl.simple.post", "</font>")

        def queryResponse = solr.query(solrParams)
        def pages = queryResponse.getResults()
        def count = pages.getNumFound()

        /**
         * 高亮替换
         */
        def highlightingMap = queryResponse.getHighlighting()
        pages.each {page->
            def hm = highlightingMap.get(page.getFieldValue("id"))
            if(hm.get("k")) {
                page.setField("k", hm.get("k"))
            }
            if(hm.get("title")) {
                page.setField("title", hm.get("title"))
            }
            if(hm.get("objName")) {
                page.setField("objName", hm.get("objName"))
            }
            if(hm.get("content")) {
                page.setField("content", hm.get("content"))
            }
        }

    }
}
