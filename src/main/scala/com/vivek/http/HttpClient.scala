package com.vivek.http

import com.vivek.http.Method._
import org.apache.http.client.methods.{HttpGet, HttpPost}
import org.apache.http.impl.client.HttpClients
import org.apache.http.{HttpResponse => ApacheResponse}

trait HttpClient {
  def get[K](path: String)(implicit convert: ApacheResponse => K): K
  def post[K](path: String)(implicit convert: ApacheResponse => K): K
}

object HttpClient {
  implicit val convert: ApacheResponse => HttpResponse = res => HttpResponse(res)
}

class DefaultHttpClient extends HttpClient {
  def get[K](path: String)(implicit convert: ApacheResponse => K): K = {
    execute(GET, path)
  }

  def post[K](path: String)(implicit convert: ApacheResponse => K): K = {
    execute(POST, path)
  }

  def execute[K](method: String, path: String)(implicit convert: ApacheResponse => K): K = {
    val httpclient = HttpClients.createDefault()

    val request = method match {
      case GET => new HttpGet(path)
      case _ => new HttpPost(path)
    }
    val response = httpclient.execute(request)

    convert(response)
  }
}






