package com.akkademy

import akka.actor.{Actor, Status}
import akka.event.Logging
import com.akkadb.{GetRequest, KeyNotFoundException, SetRequest}

import scala.collection.mutable.HashMap

class AkkaDb extends Actor {
  val map = new HashMap[String, Object]
  val log = Logging(context.system, this)

  override def receive = {
    case SetRequest(key, value) =>
      log.info("received SetRequest - key: {} value: {}", key, value)
      map.put(key, value)
      sender() ! Status.Success
    case GetRequest(key) =>
      log.info("received GetRequest - key: {}", key)
      val response: Option[Object] = map.get(key)
      response match {
        case Some(x) => sender() ! x
        case None => sender() ! Status.Failure(new
            KeyNotFoundException(key))
      }
    case o => Status.Failure(new ClassNotFoundException)
  }
}