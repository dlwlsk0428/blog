# Shadowing 팁 ~.~

```
package io.sebi.demo

fun openFile() {
	println("Opening file (top level)...")
}

class Repo {
	fun openFile() {
    	println("Opening file (repository)...")
    }
    
    fun readFile() {
        openFile()
    }
}
```

이때 readFile()을 호출한다면 Opening file (repository)... 가 나온다.

이때 Kotlin에서는 shadowing일 경우에 탑 레벨에 있는 함수를 호출 할 수 있다.

```
package io.sebi.demo

fun openFile() {
	println("Opening file (top level)...")
}

class Repo {
	fun openFile() {
    	println("Opening file (repository)...")
    }
    
    fun readFile() {
        io.sebi.demo.openFile()
    }
}
```

=>  Opening file (top level)... 이 나온다.

<br> 

참고 [Kotlin Tips](https://www.youtube.com/watch?v=mJRzF9WtCpU)
