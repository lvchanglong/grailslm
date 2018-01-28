package common


class AuthInterceptor {

    AuthInterceptor() {
        matchAll().excludes(controller:"guest").excludes(action:"image").excludes(action:"download")
    }

    boolean before() {
        if(!session.uid) {
            redirect(url:"/")
            return false
        }
        return true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }

}
