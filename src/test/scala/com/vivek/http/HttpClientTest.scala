package com.vivek.http

import org.apache.http.util.EntityUtils
import org.scalatest.FunSpec
import org.scalatest.Matchers._

class HttpClientTest extends FunSpec {
  it("returns valid http response for given get request") {
    val client = new HttpClient()
    val res1 = client.get("http://google.com")(convertToString)
    res1.statusCode should be(200)
    res1.body should include("google")

    val res: Response = client.get("http://google.com")
    res.statusCode should be(200)
    EntityUtils.toString(res.body) should include("google")

    val res2: Response  = client.post("http://google.com")
    res2.statusCode should be(405)

  }

  def convertToString(response: Response) = {
    StringHttpResponse(response.statusCode, EntityUtils.toString(response.body))
  }
}
