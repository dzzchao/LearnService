## Service
- Service 是一个在后台执行长时间运行操作而不用提供用户界面的应用组件。
- Service 生命周期（两种启动方式，两种生命周期）<br/>
正常启动：onCreate -> onStartCommand -> onDestroy <br/>
bind 启动：onCreate -> onBind -> onUnbind -> onDestroy
- onCreate() 首次启动会调用此方法，后续启动不会调用此方法。可在此方法内部做一些初始化操作，创建线程等。
- onStartCommand(Intent intent, int flags, int startId) 此方法每次启动服务都会调用。此方法调用后，服务就启动了。startId 会从1开始递增。
- onDestory() 当服务不再使用且将被销毁时，系统将调用此方法。服务应该实现此方法来清理所有资源，如线程、注册的侦听器、接收器等。
- onBind() 当其他组件想通过bindService 与服务绑定时，系统将会回调这个方法，在实现中，你必须返回一个IBinder接口，供客户端与服务进行通信，必须实现此方法，这个方法是Service 的一个抽象方法，但是如果你不允许绑定的话，返回null 就可以了。

## IntentService 的使用
