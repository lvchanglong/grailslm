package common

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: "guest", action: "index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
