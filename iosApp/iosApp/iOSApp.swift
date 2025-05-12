import SwiftUI
import HoldMasterIOShared

@main
struct iOSApp: App {
    
    @UIApplicationDelegateAdaptor(AppDelegate.self)
    var appDeledate: AppDelegate

    var body: some Scene {
        WindowGroup {
            ComposeView(root: appDeledate.rootComponent).edgesIgnoringSafeArea(.all)
        }
    }
}
