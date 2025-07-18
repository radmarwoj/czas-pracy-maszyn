# czas-pracy-maszyn
# Program do zliczania czasu pracy maszyn lutujących

Prosta aplikacja konsolowa w języku Java służąca do analizy danych z plików XML generowanych przez maszyny lutujące. Program wyszukuje i sumuje czasy lutowania poszczególnych modułów.

## Funkcje
- Parsowanie plików XML
- Wyszukiwanie czasów lutowania (na podstawie frazy "Czas w module - Moduł lutowania 1")
- Sumowanie czasów i prezentacja łącznego czasu pracy

## Przykład danych wejściowych
Zobacz plik `przykladowy_raport.xml` w katalogu `Czas pracy - SEL001` w repozytorium.  
**Uwaga:** jest to plik demonstracyjny — oryginalne raporty z maszyn mogą zawierać dane wewnętrzne i nie są udostępniane publicznie.  
Folder `Czas pracy - SEL001` symuluje dane z jednej maszyny lutującej. Docelowo program przetwarza raporty z wielu maszyn (np. SEL001, SEL002, SEL003).

## Autor
Radosław Wojciechowski
