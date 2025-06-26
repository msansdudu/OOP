import nsu.chebotareva.config.model.*

config.with {
    students = [
            new Student(githubNick: "msansdudu", fullName: "Чеботарева Анна", repoUrl: "https://github.com/msansdudu/OOP"),
            new Student(githubNick: "BarDirus", fullName: "Широков Мирон", repoUrl: "https://github.com/BarDirus/OOP")
    ]

    tasks = [
            new Task(id: "lab1", name: "Lab 1: Basics", maxPoints: 10, softDeadline: "2025-04-30", hardDeadline: "2025-05-07"),
            new Task(id: "lab2", name: "Lab 2: Inheritance", maxPoints: 15, softDeadline: "2025-05-15", hardDeadline: "2025-05-22")
    ]

    controlPoints['cp1'] = new ControlPoint(name: "Midterm", date: "2025-06-01")

    settings['repoDir'] = './repos'
    settings['reportDir'] = './reports'
}