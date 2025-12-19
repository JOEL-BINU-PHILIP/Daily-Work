// =============================
// DOM ELEMENT REFERENCES
// =============================
// These fetch elements from HTML so we can show/hide screens and update UI.
const startScreen = document.getElementById("start-screen");
const quizScreen = document.getElementById("quiz-screen");
const resultScreen = document.getElementById("result-screen");

const startButton = document.getElementById("start-btn");
const questionText = document.getElementById("question-text");
const answersContainer = document.getElementById("answers-container");

const currentQuestionSpan = document.getElementById("current-question");
const totalQuestionsSpan = document.getElementById("total-questions");
const scoreSpan = document.getElementById("score");

const finalScoreSpan = document.getElementById("final-score");
const maxScoreSpan = document.getElementById("max-score");
const resultMessage = document.getElementById("result-message");
const restartButton = document.getElementById("restart-btn");
const progressBar = document.getElementById("progress");

// =============================
// QUIZ QUESTIONS ARRAY
// =============================
// Each question has a text and multiple answers.
// "correct: true" marks which option is correct.
const quizQuestions = [
  {
    question: "What is the capital of France?",
    answers: [
      { text: "London", correct: false },
      { text: "Berlin", correct: false },
      { text: "Paris", correct: true },
      { text: "Madrid", correct: false },
    ],
  },
  {
    question: "Which planet is known as the Red Planet?",
    answers: [
      { text: "Venus", correct: false },
      { text: "Mars", correct: true },
      { text: "Jupiter", correct: false },
      { text: "Saturn", correct: false },
    ],
  },
  {
    question: "What is the largest ocean on Earth?",
    answers: [
      { text: "Atlantic Ocean", correct: false },
      { text: "Indian Ocean", correct: false },
      { text: "Arctic Ocean", correct: false },
      { text: "Pacific Ocean", correct: true },
    ],
  },
  {
    question: "Which of these is NOT a programming language?",
    answers: [
      { text: "Java", correct: false },
      { text: "Python", correct: false },
      { text: "Banana", correct: true },
      { text: "JavaScript", correct: false },
    ],
  },
  {
    question: "What is the chemical symbol for gold?",
    answers: [
      { text: "Go", correct: false },
      { text: "Gd", correct: false },
      { text: "Au", correct: true },
      { text: "Ag", correct: false },
    ],
  },
];

// =============================
// QUIZ STATE VARIABLES
// =============================
// Track question index, score, and whether answers are locked.
let currentQuestionIndex = 0;
let score = 0;
let answersDisabled = false;

// Set UI values
totalQuestionsSpan.textContent = quizQuestions.length;
maxScoreSpan.textContent = quizQuestions.length;

// =============================
// EVENT LISTENERS
// =============================
startButton.addEventListener("click", startQuiz);
restartButton.addEventListener("click", restartQuiz);

// =============================
// START QUIZ
// =============================
function startQuiz() {
  // Reset quiz data
  currentQuestionIndex = 0;
  score = 0;
  scoreSpan.textContent = 0;

  // Switch screens
  startScreen.classList.remove("active");
  quizScreen.classList.add("active");

  showQuestion();
}

// =============================
// SHOW QUESTION
// =============================
function showQuestion() {
  // Allow answering again
  answersDisabled = false;

  const currentQuestion = quizQuestions[currentQuestionIndex];

  // Update UI display
  currentQuestionSpan.textContent = currentQuestionIndex + 1;

  // Update progress bar width
  const progressPercent = (currentQuestionIndex / quizQuestions.length) * 100;
  progressBar.style.width = progressPercent + "%";

  // Set question text
  questionText.textContent = currentQuestion.question;

  // Clear previous answers
  answersContainer.innerHTML = "";

  // Loop over each answer choice
  currentQuestion.answers.forEach((answer) => {
    const button = document.createElement("button");
    button.textContent = answer.text;
    button.classList.add("answer-btn");

    // dataset.correct stores extra data directly in HTML element
    button.dataset.correct = answer.correct;

    // Add click event for each answer button
    button.addEventListener("click", selectAnswer);

    // Add button to answer container
    answersContainer.appendChild(button);
  });
}

// =============================
// HANDLE ANSWER SELECTION
// =============================
function selectAnswer(event) {
  // Prevent clicking multiple answers
  if (answersDisabled) return;
  answersDisabled = true;

  const selectedButton = event.target;
  const isCorrect = selectedButton.dataset.correct === "true";

  // Make all correct answers green, selected wrong one red
  Array.from(answersContainer.children).forEach((button) => {
    if (button.dataset.correct === "true") {
      button.classList.add("correct");
    } else if (button === selectedButton) {
      button.classList.add("incorrect");
    }
  });

  // Update score
  if (isCorrect) {
    score++;
    scoreSpan.textContent = score;
  }

  // Move to next question after 1 second
  setTimeout(() => {
    currentQuestionIndex++;

    if (currentQuestionIndex < quizQuestions.length) {
      showQuestion();
    } else {
      showResults();
    }
  }, 1000);
}

// =============================
// SHOW RESULTS SCREEN
// =============================
function showResults() {
  quizScreen.classList.remove("active");
  resultScreen.classList.add("active");

  finalScoreSpan.textContent = score;

  const percentage = (score / quizQuestions.length) * 100;

  // Set result message based on performance
  if (percentage === 100) {
    resultMessage.textContent = "Perfect! You're a genius!";
  } else if (percentage >= 80) {
    resultMessage.textContent = "Great job! You know your stuff!";
  } else if (percentage >= 60) {
    resultMessage.textContent = "Good effort! Keep learning!";
  } else if (percentage >= 40) {
    resultMessage.textContent = "Not bad! Try again to improve!";
  } else {
    resultMessage.textContent = "Keep studying! You'll get better!";
  }
}

// =============================
// RESTART QUIZ
// =============================
function restartQuiz() {
  resultScreen.classList.remove("active");
  startQuiz(); // Simply call startQuiz again
}
