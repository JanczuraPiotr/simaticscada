SimaticScada
============

Projekty ** Simatic* ** tworzą oprogramowanie do komunikacji ze sterownikami przemysłowymi Simatic S7-1200 firmy  Simens.
Prawdopodobnie będą mogły pracować z pozostałymi sterownikami firmy Simens obsługującymi protokół PROFINET ale miałem dostęp tylko do sterownika S7-1200.
Nie są kompletnym rozwiązaniem a jedynie prototypem utworzonym w celu poznania możliwości zastosowania technologii webowej do nadzoru nad sieciami przemysłowymi.

Ten program jest prototypem (namiastką) systemu SKADA od nadzoru nad procesami przemysłowymi. Śledzi stan stan czterech zmiennych, jednego portu wejściowego i jednego portu wyjściowego pozwalające jednocześnie na ich modyfikację. Nie jest wymagany do spełnienia założeń postawionych przez pomysłodawcę, do tego celu wystarczająca jest aplikacja SimaticWeb. Uznałem jednak, że pakiet będzie bardziej kompletny gdy będzie możliwość nadzoru nad procesem w trybie ciągłym.

Przebudowy wymaga: Obsługa strumieni z socket, system komend by komendy samodzielnie nie komunikowały się poprzez strumień, komunikacja z SimaticServer nie powinna sprawdzać stanu procesu poprzez odpytywanie servera ale server powinien informować o zmianach zmiennych.