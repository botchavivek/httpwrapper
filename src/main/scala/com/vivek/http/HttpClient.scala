package com.vivek.http

import org.apache.http.HttpEntity
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients

class HttpClient {
  def get[T,K](path: String)(convert: Response => K)(block: K => T): T = {
    val httpclient = HttpClients.createDefault();
    val response = httpclient.execute(new HttpGet(path))
    block(convert(HttpResponse(response.getStatusLine.getStatusCode, response.getEntity)))
  }
}

trait Response {
  def statusCode: Int
  def body: HttpEntity
}

case class HttpResponse(statusCode: Int, body: HttpEntity) extends Response
case class StringHttpResponse(statusCode: Int, body: String)


