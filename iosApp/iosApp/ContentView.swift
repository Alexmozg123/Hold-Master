import UIKit
import SwiftUI
import HoldMasterIOShared

class AppDelegate: NSObject, UIApplicationDelegate {
    
    var rootComponent: RootComponent

    override init() {
        // Инициализация Koin
        PlatformSDK().doInit(config: PlatformConfig())
    
        // Получение фабрики корневого компонента через Koin
        let factory = Inject().getRootFactory()
            
        // Создание корневого компонента
        rootComponent = factory.invoke(componentContext: DefaultComponentContext(lifecycle: ApplicationLifecycle()))
    }
}

struct ComposeView: UIViewControllerRepresentable {
    
    let root: Root
    
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(rootComponent: root)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}


