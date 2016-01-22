package com.vivek.http

import com.sun.deploy.net.HttpUtils
import org.apache.http.util.EntityUtils
import org.scalatest.FunSpec
import org.scalatest.Matchers._
/**
  * Created by vivek on 22/01/2016.
  */
class HttpClientTest extends FunSpec {
  it("returns valid http response for given get request") {
    val client = new HttpClient()
    client.get("http://google.com")(convertToString) { res =>
      res.statusCode should be(200)
      res.body should include("google")
    }
  }

  def convertToString(response: Response) = {
    StringHttpResponse(response.statusCode, EntityUtils.toString(response.body))
  }
}
