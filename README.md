# Java Quiz App

## Technologies Used

1. JavaFX
2. Maven
3. MongoDB Java Driver

## Issues to fix

```
[x] Add Back Button in QuizMaster's result page
[x] Add Cancel Button in QuizMaster's Create Quiz Wizard
[ ] Add Cancel Button in Quiz Participants Quiz List View
[x] Make UI Better
[ ] When viewing Leaderboard (result.fxml), initial width should be a bit higher
[ ] LocalDate.now().toString() stores createdOn and attemptedOn Strings in a weird format, store it in proper DD/MM/YY-HH:MM:SS format
[x] Database Singleton Class
```

## MongoDB Database Structure

### `user` collection
1. quizmaster type
<img width="231" alt="image" src="https://user-images.githubusercontent.com/68689014/234000855-ca28413c-7d21-4153-98cc-f2e38bcfb66d.png">
2. participant type
<img width="231" alt="image" src="https://user-images.githubusercontent.com/68689014/234001067-e0b8aab6-7a3e-4d31-b59d-fa30e21e57c0.png">

### `quiz` collection
<img width="231" alt="image" src="https://user-images.githubusercontent.com/68689014/234000484-cac054df-d35a-47a2-99d4-e6d4aebd5c3c.png">

### `result` collection
<img width="231" alt="image" src="https://user-images.githubusercontent.com/68689014/234001243-290d8ed3-448d-4910-a9d9-c1cad3049551.png">
