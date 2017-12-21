# Software Testing Project Source Code - Math Calculator Program


## Output Pictures

The output on the browser when all of the specs "passed"

![alt text](http://i67.tinypic.com/11uhjpv.png)

The output on the browser when the spec that tested the sum function "failed"

![alt text](http://i67.tinypic.com/2s9zsds.png)

To get the failure output on the browser I changed this spec

```
//Spec for sum operation
        it("should be able to calculate sum of 3 and 5", function() {
            expect(calc.sum(3,5)).toEqual(8);
        });
```

to this spec which expects a 9 instead of an 9 for the sum of 3 and 5
```
//Spec for sum operation
        it("should be able to calculate sum of 3 and 5", function() {
            expect(calc.sum(3,5)).toEqual(9);
        });
```

### Source Code Credit
The Credit goes to

* https://howtodoinjava.com/scripting/javascript/jasmine-javascript-unit-testing-tutorial/

For supplying the source code and the tutorial in order to run the tests.

