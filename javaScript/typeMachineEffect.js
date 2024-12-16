const texts = ["Informatiker Applikationsentwicklung", "im ersten Lehrjahr"];
let currentText = 0;
let character = 0;

const textElement = document.querySelector("#startPageTitle span");

function typeWriter() {
  if (character <= texts[currentText].length) {
    textElement.textContent = texts[currentText].substring(0, character);
    character++;
    setTimeout(typeWriter, 50);
  } else {
    deleteText();
  }
}

function deleteText() {
  if (character != 0) {
    character--;
    textElement.textContent = texts[currentText].substring(0, character);
    setTimeout(deleteText, 50);
  } else {
    currentText += 1;
    if(currentText == texts.length){
        currentText = 0;
    }
    typeWriter()
  }
}

typeWriter();
