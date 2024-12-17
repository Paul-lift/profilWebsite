//PROJEKTE

const allProjectsBlocks = document.querySelectorAll(".projectsBlock");
const appearanceLine = window.innerHeight * (4/5);
window.addEventListener("scroll", flyInAnimationProject);

function flyInAnimationProject(){
  allProjectsBlocks.forEach(function(projectsBlock){
    const differenceContainerToTopProjects = projectsBlock.getBoundingClientRect().top;

    if(differenceContainerToTopProjects < appearanceLine){
      projectsBlock.classList.add("showprojectsBlock")
      projectsBlock.classList.remove("hidden")
    } else {
      projectsBlock.classList.remove("showprojectsBlock")
      projectsBlock.classList.add("hidden")
    }
  })
}

//Skills and Languages

const allSkillsAndLanguageContainers = document.querySelectorAll(".skillsAndLanguagesContainer");
window.addEventListener("scroll", flyInAnimationskillsAndLanguages); //bei jedem scorll soll dann diese funktion ausgeführt werden 

allSkillsAndLanguageContainers.forEach(function(skillsAndLanguagesContainer){ 
  skillsAndLanguagesContainer.classList.add("hidden")
});

function flyInAnimationskillsAndLanguages() {     //diese funktion wird bei jedem scroll ausgeführter
  allSkillsAndLanguageContainers.forEach(function(skillsAndLanguagesContainer) {

    //hier nimmt er dann die pixelhöhe vom oberenteil des containers bis oben vom viewport
    const differenceContainerToTopskillsAndLanguages = skillsAndLanguagesContainer.getBoundingClientRect().top;

    /*wenn die pixelhöhe vom oberen teil des containers bis zum top des viewports kleiner ist als 
    die pixelhöhe von 4/5 des viewports, dann werden die container angezeigt, wenn nicht denn werden sie ausgeblendet*/
    if (appearanceLine > differenceContainerToTopskillsAndLanguages) {
      skillsAndLanguagesContainer.classList.add("showSkillsAndLanguages");
      skillsAndLanguagesContainer.classList.remove("hidden");
    } else {
      skillsAndLanguagesContainer.classList.add("hidden");
      skillsAndLanguagesContainer.classList.remove("showSkillsAndLanguages");
    }
  });
}
