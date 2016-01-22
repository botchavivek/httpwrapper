package com.vivek.http

import org.apache.http.HttpEntity
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients

class HttpClient {

  implicit val convert:  Response => Response = res => res


  def get[K](path: String)(implicit convert: Response => K): K = {
    val httpclient = HttpClients.createDefault();
    val response = httpclient.execute(new HttpGet(path))
    convert(HttpResponse(response.getStatusLine.getStatusCode, response.getEntity))
  }
}

trait Response {
  def statusCode: Int
  def body: HttpEntity
}

case class HttpResponse(statusCode: Int, body: HttpEntity) extends Response
case class StringHttpResponse(statusCode: Int, body: String)


