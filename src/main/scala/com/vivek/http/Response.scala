package com.vivek.http

import org.apache.http.util.EntityUtils
import org.apache.http.{HttpEntity, HttpResponse => ApacheResponse}

object Response {
  def convertToString(response: ApacheResponse) = {
    StringHttpResponse(response)
  }
}

trait Response[T] {
  self: {def response: ApacheResponse} =>
  def statusCode: Int = response.getStatusLine.getStatusCode
  def body: T
}

case class HttpResponse(response: ApacheResponse) extends Response[HttpEntity] {
  override def body = response.getEntity
}

case class StringHttpResponse(response: ApacheResponse) extends Response[String] {
  override def body = EntityUtils.toString(response.getEntity)
}
