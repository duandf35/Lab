const Singleton = (function() {
    const instance = new Object();

    return function() {
        // the 'constructor' function will return the 'instance'
        return instance;
    }
})();

/*
 * Per MDN: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/new
 *
 * When the code new Foo(...) is executed, the following things happen:
 * - A new object is created, inheriting from Foo.prototype.
 *
 * - The constructor function Foo is called with the specified arguments,
 * and with this bound to the newly created object. new Foo is equivalent to new Foo(),
 * i.e. if no argument list is specified, Foo is called without arguments.
 *
 * - The object returned by the constructor function becomes the result of the whole new
 * expression. If the constructor function doesn't explicitly return an object, the object created
 * in step 1 is used instead. (Normally constructors don't return a value, but they can choose
 * to do so if they want to override the normal object creation process.)
 */
let s1 = new Singleton();
let s2 = new Singleton();
let s3 = Singleton();

console.log(`s1 === s2 ${s1 === s2}`);
console.log(`s2 === s3 ${s2 === s3}`);
