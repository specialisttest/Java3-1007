module ru.specialist.library1 {
	//requires указывает модули, от которых зависит текущий модуль
	requires java.base;
	requires java.sql;
	
	
	//requires transitive — транзитивная зависимость 
	// если модуль m1 транзитивно зависит от модуля m2,
	// и есть третий модуль m3, который зависит от m1 то
	// модуль m3 будет иметь доступ также и к m2
	//requires transitive jdk.compiler;
	
	//requires static позволяет указать compile-time зависимости
	//requires static java.xml;
	
	// exports указывает пакеты, которые экспортирует текущий модуль
	// (не включая вложенные)
	exports ru.specialist.mylib;
	
	// Ограничивает доступ к пакету только другим модулем 
	//exports ru.specialist.mylib to ru.specialist.another_module;
	
	//uses указывает, какие сервисы использует модуль
	//uses java.sql.Connection;
	
	//provides указывает, какие сервисы предоставляет модуль
	//provides ru.specialist.MyInterface with
	//	ru.specialist.mylib.MyInterfaceImpl;
	
	//Iterable<MyInterface> service = 
	//			ServiceLoader.load(MyInterface.class)
	
	// --module-path C:\javafx-sdk-17.0.1\lib --add-modules javafx.controls,javafx.fxml,javafx.web
	
	// открыть модуль для reflection доступа
	//opens ru.specialist.mylib;
}