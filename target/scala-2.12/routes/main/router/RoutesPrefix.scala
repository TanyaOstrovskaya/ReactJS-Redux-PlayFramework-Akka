
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/user/Desktop/play-test/run/conf/routes
// @DATE:Wed Aug 23 09:15:47 MSK 2017


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
