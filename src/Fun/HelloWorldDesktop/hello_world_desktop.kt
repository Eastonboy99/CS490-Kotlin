package Fun.HelloWorldDesktop

class MainView : View() {
    override val root = group { label("Hello World") }
}

class TornadoApp : App(MainView::class)