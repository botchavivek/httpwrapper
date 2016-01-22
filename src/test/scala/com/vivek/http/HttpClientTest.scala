package com.vivek.http

import com.vivek.http.Response._
import org.apache.http.util.EntityUtils
import org.scalatest.FunSpec
import org.scalatest.Matchers._
import com.vivek.http.HttpClient._

class HttpClientTest extends FunSpec {
  it("returns valid http response for given get request") {
    val client = new DefaultHttpClient()
    val res1 = client.get("http://google.com")(convertToString)
    res1.statusCode should be(200)
    res1.body should include("google")

    val res:HttpResponse = client.get("http://google.com")
    res.statusCode should be(200)
    EntityUtils.toString(res.body) should include("google")

    val res2:HttpResponse  = client.post("http://google.com")
    res2.statusCode should be(405)

  }


}
