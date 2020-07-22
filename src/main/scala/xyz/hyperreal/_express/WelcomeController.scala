package xyz.hyperreal._express

import scalajs.js

import typings.express.{mod => expressMod}
import typings.expressServeStaticCore.mod._

object WelcomeController {

  val router: Router = expressMod.Router()

  private val index: RequestHandler[Unit, String, Unit, Unit] =
    (req, res, next) => res.send("Hello, World!")

  trait HasName extends js.Object {
    val name: js.UndefOr[String]
  }

  private val getName: RequestHandler[HasName, String, Unit, Unit] = (req, res, next) =>
    res.send(s"Hello, ${req.params.name.getOrElse("No Name")}!")

  private val getId: RequestHandler[js.Dictionary[String], String, Unit, Unit] = (req, res, next) =>
    res.send(s"ID: ${req.params get "id" getOrElse "No ID"}!")

  router
    .get[Unit, String, Unit, Unit]("/", index)
    .get[HasName, String, Unit, Unit]("/name/:name", getName)
    .get[js.Dictionary[String], String, Unit, Unit]("/id/:id", getId)

}
