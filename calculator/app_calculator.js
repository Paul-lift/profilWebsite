//Display
const display = document.querySelector(".display");

//Selects numbers from Document and pushes it to an Array from 0-9
let allNumbers = [];
for (let i = 0; i <= 9; i++) {
    allNumbers.push(document.querySelector(`.number${i}`));
}

//Comma
const comma = document.querySelector('.comma')

// Operators
const equal = document.querySelector('.equal');
const addition = document.querySelector('.addition');
const subtraction = document.querySelector('.subtraction');
const multiply = document.querySelector('.multiply');
const division = document.querySelector('.division');

// Clear and Delete and Brackets
const clear = document.querySelector('.clear');
const deleteButton = document.querySelector('.delete');
const openingBracket = document.querySelector('.openingBracket');
const closingBracket = document.querySelector('.closingBracket');

//Array for the Numbers which are currently visible on the Display
let numbersOnDisplayArray = []

//Delete Button which deletes the last Object from the Array
deleteButton.addEventListener("click", function () {
    numbersOnDisplayArray.pop();
    display.textContent = numbersOnDisplayArray.join("");
})

//sets the array lenght to zero
clear.addEventListener("click", function () {
    numbersOnDisplayArray.length = 0;
    oldOperation.length = 0;
    display.textContent = numbersOnDisplayArray;
});

//Creates for every Button a Function which adds eventlistener
allNumbers.forEach(function (button, index) {
    button.addEventListener("click", function () {
        numbersOnDisplayArray.push(index.toString());
        display.textContent = numbersOnDisplayArray.join("");
    });
});

//pushes brackets into displayarray
openingBracket.addEventListener("click", function () {
    numbersOnDisplayArray.push("(");
    display.textContent = numbersOnDisplayArray.join("");
})

closingBracket.addEventListener("click", function () {
    numbersOnDisplayArray.push(")");
    display.textContent = numbersOnDisplayArray.join("");
})

const operatorsStringWithComma = ["+", "-", "*", "/", "."];

//checks if the last element of the Display is included in operatorsStringWithComma
function isLastElementOperatorOirComma() {
    return operatorsStringWithComma.includes(numbersOnDisplayArray[numbersOnDisplayArray.length - 1])
}

//Functions which pushes clicked Operators in to the Array and displays the Array on the Display, only if the last Symbol ISN'T an Operator
addition.addEventListener("click", function () {
    if (isLastElementOperatorOirComma()) {
        return;
    } else {
        numbersOnDisplayArray.push("+");
        display.textContent = numbersOnDisplayArray.join("");
    }
});

subtraction.addEventListener("click", function () {
    if (isLastElementOperatorOirComma()) {
        return;
    } else {
        numbersOnDisplayArray.push("-");
        display.textContent = numbersOnDisplayArray.join("");
    }
});

multiply.addEventListener("click", function () {
    if (isLastElementOperatorOirComma()) {
        return;
    } else {
        numbersOnDisplayArray.push("*");
        display.textContent = numbersOnDisplayArray.join("");
    }
});

division.addEventListener("click", function () {
    if (isLastElementOperatorOirComma()) {
        return;
    } else {
        numbersOnDisplayArray.push("/");
        display.textContent = numbersOnDisplayArray.join("");
    }
});

//pushes comma into displayarray
comma.addEventListener("click", function () {
    if (isLastElementOperatorOirComma()) {
        return
    } else {
        numbersOnDisplayArray.push(".");
        display.textContent = numbersOnDisplayArray.join("");
    }
});

/*Displays the Solution on Display, deletes old Array and pushes the result in to the Array,
 if no new operations, takes last operation and does it again*/

let oldOperation = [];
const operatorsString = ["+", "-", "*", "/"];

equal.addEventListener("click", function () {

    if (operatorsString.some(function (operator) {
        return numbersOnDisplayArray.includes(operator) //if contains a operator returns true
    }) == true) {
        oldOperation = numbersOnDisplayArray.slice();   //creates copy of the numberOnDisplay array
        let numbersOnDisplayArrayWithoutCommas = numbersOnDisplayArray.join(""); //array without commas

        if (numbersOnDisplayArrayWithoutCommas.includes("/0")) {
            alert("You can't dive by zero")
        } else {
            display.textContent = eval(numbersOnDisplayArrayWithoutCommas.toString()) //converts Array (without commas) to a String, then to code
            numbersOnDisplayArray.length = 0; //clears the numbersOnDisplayArray
            numbersOnDisplayArray.push(eval(numbersOnDisplayArrayWithoutCommas.toString())); //pushes the result in to the numbersOnDisplayArray
        }

    } else {

        //gets the last Index of any Operator

        let lastIndex = Math.max(
            oldOperation.lastIndexOf('+'),
            oldOperation.lastIndexOf('-'),
            oldOperation.lastIndexOf('*'),
            oldOperation.lastIndexOf('/')
        )

        let lastOperation = oldOperation.slice(lastIndex);    //variabel for last Operation, slices from last operator until the end

        numbersOnDisplayArray.push(lastOperation.join(""))    //pushes the last Operation into the numbersOnDisplayArray
        numbersOnDisplayArrayWithoutCommas = numbersOnDisplayArray.join("");    //array without commas
        display.textContent = eval(numbersOnDisplayArrayWithoutCommas.toString()) //displays the new result
    }
});

