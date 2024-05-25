# Rendszerterv

## 1. A rendszer célja 

A rendszer célja, hogy megkönnyítse az autókereskedés számára, mind a járművek eladását, mint a járművek nyilvántartását. 
A rendszerben tárolni lehet a meglévő járművek különféle adatait és időpontot lehet rendelni azokhoz. Ezen időpontok közül választva 
tudja a vásárló megtekinteni személyesen a kiválasztott járművet, a bejelentkezést követően. Ha az adott felhasználó nem rendelkezik fiókkal,
bármikor regisztrálhat egyet de, ha csak a jármű kinálatot szeretné megtekinteni akkor azt bejelentkezés nélkül is megteheti. <br>

## 2. Projektterv

Projektszerepkörök, felelőségek: <br>
Scrum Masters: Cégünk Vezető programozói <br>
Product Owner: Oláh Krisztián, Oláh Család <br>
Contributer: Dávid Buda <br>
Contributer: Kémeri Martin <br>
Projektmunkások és felelőségek: <br>
Frontend: Kémeri Martin <br>
Backend: Dávid Buda <br>
Tesztelés: Dávid Buda, Kémeri Martin <br>

|Funkció                  | Feladat                                | Becslés (nap) | Aktuális becslés (nap) | Eltelt idő (nap) | Becsült idő (nap) |
|-------------------------|----------------------------------------|---------------|------------------------|------------------|---------------------|
|Rendszerterv             |Megírás                                 |             1 |                      1 |                1 |                   1 |
|Program                  |Adatbázisrendszer elkészítése           |             1 |                      1 |                1 |                   1 |
|Program                  |Frontend elkészítése                    |             8 |                      8 |                8 |                   8 |
|Program                  |Backend elkészítése                     |             8 |                      8 |                8 |                   8 |
|Program                  |Tesztelés és hibajavítás                |             2 |                      2 |                2 |                   2 |

## 3. Követelmények

- Java és JavaFx-ben történő megvalósítás<br>
- Az adatok adatbázisban való tárolása<br>
- Jogosultságrendszer kialakítása<br>
- Admin jogosultsággal válnak szerkeszthetővé csak az adatok<br>
- Bejelentkezés nélkül is megtekinthetővé váljon a kinálat<br>

## 4. Üzleti folyamatok modellje

![Untitled Diagram drawio](https://github.com/103470/Pogtech/assets/114563545/9aeb7dac-5eda-42d8-8672-c4059b59e864)


## 5. Funkcionális terv

### Rendszerszereplők:<br>
- Admin<br>
- User<br>
- Látogató<br>

### Rendszerhasználati esetek és lefutásuk:<br>

Adminisztrátor:<br>

- Teljes hozzáférése van a rendszerhez<br>
- Adatbázis tartalmát láthatja, válltoztathatja<br>
- Új járművek felvétele, időpont hozzárendelése<br>
- Meglévő járművek törlése/szerkesztése<br>

User:<br>

- Bejelentkezést kovetően, időpont foglalása személyes megtekintésre


Látogató:<br>

- Böngészheti a cég járműkinálatát



## 6. Fizikai környezet


## 7. Architektúrális terv

### Web Szerver<br>

XAMPP<br>

### Adatbázis<br>

MySQL alapú adatbázis rendszer<br>

### Program Hozzáférés<br>

XAMPP, ami az installáció esetén egy szervergép
JDBC (Java Database Connectivity) API az adatbázishoz való hozzáféréshez<br>

## 8. Adatbázis terv
## 9. Implementációs terv

A projektet két részre oszlik: a frontendre és a backendre. A frontend JavaFX segítségével készül, míg a backend Java keretrendszerben.
A szerver és az adatbázist összekötő csomag felelős az adatbázis kezeléséért. A fejlesztéshez szükség van az IntelliJ telepítésére és konfigurálására.

### Backend 
- Felhasználó kezelés megvalósítása<br>
- Járműkezelés megvalósítása<br>
- Időpont foglalási lehetőség megvalósítása<br>

### Frontend
- Felhasználó felület kialakítása JavaFX segítségével<br>

## 10. Teszt terv


## 11. Telepítési terv

### Fizikai telepítés

- A rendszer telepítése a szerver hardverére vagy a felhasználók számítógépeire<br>

### Szoftver telepítési terv

- A felhasználónak szüksége van egy Windows 10 vagy 11 operációs rendszerre, amely támogatja a Java alkalmazásokat<br>
- A szoftverünk futtatható Windows szerveren
- Szükség van valamilyen adatbázis szerverre, például MySQL:<br>
   - Szükséges telepíteni az XAMPP nevű szoftvert<br>
   - Az adatbázis konfigurálása az XAMPP segítségével történik<br>
- A fejlesztők számára az alkalmazás szabadon konfigurálható, fejleszthető<br>
- Abban az esetben, ha a szükséges beállítások megtörténtek, a felhasználók számára az alkalmazás futtatható<br>




## 12. Karbantartási terv
