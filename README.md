# Phpx
Phpx is PHP interpreter written Java. 
An alternative jPHP, jRuby, Jython, Scala and other JVM launguages.

# Temporary changes of syntax
- If you calling a operator function, don't add semicolon to end of function.
- Now not supports single quotes as string.
- Don't supports classes(class *** {}, new ***)

# Examples
```php
<?php
use php\bundles\MathBundle; // Built-in math functions of the extension. Over 25+ math functions

// Defining variables and constants
$a = 1;
define("a", 0)

// Java math
print("PI: ".PI)
print("Sin PI: ".sin(PI)) // With MathExt
print("Cos PI: ".cos(PI)) // With MathExt

// If/else statement (elseif not supports)
if($a == 1){
	print("Variable a equals 1")
}else{
	print("Variable a not equals 1")
}
if(a == 1){
	print("Constant a equals 1")
}else{
	print("Constant a not equals 1")
}

// Functions
function test(){ // ( P.S: Anonymous functions not supports :( )
	return 0; // Return statement
}

// Ternary expressions
print(test() ? "Test returned 1" : "Test returned 0")

// Compiled assembly has size 100-500kb it's very low! (without extensions, and java size)
```
