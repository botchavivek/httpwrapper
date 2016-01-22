package com.vivek.http

import org.apache.http.HttpEntity
import org.apache.http.client.methods.{HttpPost, HttpGet}
import org.apache.http.impl.client.HttpClients

class HttpClient {

  implicit val convert:  Response => Response = res => res


  def get[K](path: String)(implicit convert: Response => K): K = {
    execute(GET, path)
  }

  def post[K](path: String)(implicit convert: Response => K): K = {
    execute(POST, path)
  }

  def execute[K](method: Method, path: String)(implicit convert: Response => K): K = {
    val httpclient = HttpClients.createDefault()

    val request = method match {
      case GET => new HttpGet(path)
      case _ => new HttpPost(path)
    }
    val response = httpclient.execute(request)

    convert(HttpResponse(response.getStatusLine.getStatusCode, response.getEntity))
  }
}

abstract class Method(name: String)
object GET extends Method("get")
object POST extends Method("post")


trait Response {
  def statusCode: Int
  def body: HttpEntity
}

case class HttpResponse(statusCode: Int, body: HttpEntity) extends Response
case class StringHttpResponse(statusCode: Int, body: String)


