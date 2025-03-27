# Java Game of Life

## 📌 Overview  
This project is a Java implementation of a **Game of Life**, developed as part of the **Design Patterns** course in the third year of a Computer Science degree at **Université du Mans**. The program offers multiple simulation modes and follows object-oriented design principles.

## 🎮 Features  
- 🏗 **Modular Design**: Uses well-structured design patterns.  
- 🎨 **Multiple Modes**: Different game variations available.  
- ⏸ **Pause Button**: Allows stopping and resuming the simulation.  
- ⏭ **Next Generation Button**: Manually step through generations.  
- ⏳ **Speed Slider**: Adjust the simulation speed dynamically.  
- 🔽 **Mode Selection**: Choose between different rule sets:  
  - Conway  
  - LifeWithoutDeath  
  - HighLife  
  - DayAndNight  
  - Chaos  
  - Diamoeba  
  - Replicator  
- 🎲 **Pattern Selection**: Initialize the grid with predefined patterns:  
  - Glider  
  - Gosper Glider Gun  

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
- **Visitor**: Allows new operations to be added to existing structures without modifying them.  

---

🔥 Developed by **Pixis-py** | Université du Mans 🎓
