# Java Game of Life

## 📌 Overview  
This project is a Java implementation of the **Game of Life**, developed as part of the **Design Patterns** course in the third year of a Computer Science degree at **Université du Mans**. The program offers multiple simulation modes and follows object-oriented design principles, applying various design patterns to enhance modularity and extensibility.

## 🎮 Features  
- 🏗 **Modular Design**: Utilizes well-structured design patterns for flexibility.  
- 🎨 **Multiple Modes**: Various game rule sets available:  
  - Conway  
  - LifeWithoutDeath  
  - HighLife  
  - DayAndNight  
  - Chaos  
  - Diamoeba  
  - Replicator  
- ⏸ **Pause/Resume Button**: Allows stopping and resuming the simulation at any point.  
- ⏭ **Next Generation Button**: Advances the simulation by one generation at a time, allowing for manual step-through.  
- ⏳ **Speed Slider**: Adjusts the simulation speed dynamically for real-time control.  
- 🔽 **Mode Selection**: Choose between different rule sets for the game:  
  - Classical  
  - High Life  
  - Day and Night  
  - Diamoeba  
  - Replicator  
  - Life Without Death  
  - Chaos  
- 🎲 **Pattern Selection**: Initialize the grid with predefined patterns:  
  - Glider  
  - Gosper Glider Gun  
  - Glider  
  - Cannon  
  - Labyrinth (Replicator)  
  - Explosive (Replicator)  
  - Replicator

## 🛠 Installation & Usage  
### 🔹 Prerequisites  
- Java **11+** installed  
- Maven (optional, for dependency management)  

### 🔹 Clone the Repository  
```sh
git clone git@github.com:Pixis-py/JavaGameOfLife.git
cd JavaGameOfLife
```

### 🔹 Compile and Run  
If using **Maven**, you can build and run with:  
```sh
mvn clean package
java -jar target/JDV-1.0-SNAPSHOT.jar
```

## 📁 Project Structure  
```
JavaGameOfLife/
│── src/                 # Source code
│   ├── main/            # Game folder
│   ├── test/            # Test folder
│── pom.xml              # Maven configuration
│── README.md            # Project documentation
└── .gitignore           # Ignored files
```

## 🏗 Design Patterns Used  

- **Observer / Observable**: Manages event-driven updates between components.
- **Visitor**: Allows new operations (game modes) to be added to existing structures without modifying them.
- **Singleton**: Ensures that each cell is instantiated only once and is shared throughout the game

---

🔥 Developed by **Pixis-py** | Université du Mans 🎓
