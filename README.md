# Enigma Machine – Java Implementation

## Overview

This project is a Java implementation of the Enigma cipher machine, originally developed and used by Germany during World War II.

The purpose of this project is to recreate the core functionality of an Enigma machine through a modular object-oriented design. Each component is implemented as an individual class, allowing the machine's internal operations to be separated and managed independently.

The current implementation focuses on the interaction between the different components, including rotor-based substitution, signal reflection, and plugboard connections.

## Requirements

- **Java Development Kit (JDK) 25**

The project is developed using Java 25 and requires a compatible JDK to compile and run.

The JavaFX `Pair` class is currently used for passing configuration data between components.

## Architecture

The project consists of five core components:

| Class | Responsibility |
|---|---|
| `Alphabet` | Defines the alphabet and provides character-to-index conversion. |
| `Rotor` | Handles character substitution, signal routing, and rotor positioning. |
| `Plugboard` | Applies letter substitutions before and after the rotor assembly. |
| `Reflector` | Reflects the incoming signal and sends it back through the rotor assembly. |
| `EnigmaMachine` | Initializes the components and coordinates the encryption process. |

### Main

The `Main` class currently serves as the application's testing environment.

It contains predefined rotor wiring configurations and plugboard connections used to initialize an `EnigmaMachine` instance.

The class performs two primary operations:

- **Encryption:** Processes a predefined plaintext message through the machine.
- **Decryption:** Resets the machine to its original rotor positions and processes the encrypted message using the same configuration.

Since the Enigma machine uses a reciprocal encryption process, the same configuration can be used for both encryption and decryption.

The `Main` class is currently intended for development and testing purposes rather than direct user interaction.

Future development will repurpose this class as the application's entry point for a terminal-based interface.

### Alphabet

The `Alphabet` class provides shared constants and utility methods used throughout the implementation.

It defines the standard English alphabet (`A-Z`) and its size of 26 characters.

Two static methods are provided:

- `GET_LETTER_INDEX(char input)` – Converts an uppercase character into its corresponding zero-based alphabet index.
- `GET_LETTER_FROM_INDEX(int index)` – Converts an alphabet index back into its corresponding character.

This class centralizes alphabet-related operations and avoids repeating the same conversion logic throughout the project.

### Rotor

The `Rotor` class represents an individual rotor and is responsible for character substitution and signal routing.

Each rotor contains:

- A wiring configuration represented by a string.
- A current rotor position.
- References to the previous and next rotors.
- A reference to the reflector.

**Character substitution**

The rotor performs substitution in two directions.

`forwardCipher(char input)` processes a character using the rotor's wiring configuration. The current rotor position is added to the input character's alphabet index before accessing the wiring string.

`reverseCipher(char input)` performs the inverse operation. It locates the incoming character in the wiring configuration and calculates the corresponding character while accounting for the rotor's current position.

This allows the signal to travel through the rotor assembly in both directions.

**Signal routing**

Two methods manage signal transmission:

- `cipherCharacterNextRotor(char input)` – Processes the character through the rotor's forward wiring and passes it to the next rotor. If no next rotor exists, the signal is sent to the reflector.
- `cipherCharacterPreviousRotor(char input)` – Processes the character through the reverse wiring and passes it to the previous rotor. If no previous rotor exists, the resulting character is returned.

**Rotor positioning**

The `updateRotorPosition()` method increments the rotor's position by one, using modulo 26 to keep it within the alphabet's range.

When a rotor completes a full rotation and returns to position zero, it triggers the next rotor to advance by one position.

This currently provides a basic sequential rotation mechanism without the notch-based turnover behaviour of historical Enigma rotors.

### Plugboard

The `Plugboard` class handles character substitutions before and after the signal passes through the rotor assembly.

Its default configuration maps every character to itself, meaning that no substitutions occur unless connections are configured.

The `setup()` method accepts a list of character pairs. Each pair represents a connection between two letters.

Connections are bidirectional. If A is connected to B, an incoming A becomes B, and an incoming B becomes A.

The `passthrough(char input)` method retrieves the corresponding character from the configured plugboard mapping.

The class also contains a `hasDuplicates()` method, which checks whether a character appears in more than one connection. This can be used to validate plugboard configurations.

### Reflector

The `Reflector` class represents the reflector component at the end of the rotor assembly.

Its primary responsibility is to redirect the incoming signal back through the rotors in the opposite direction.

The reflector currently uses a numerical transposition rather than a predefined wiring configuration.

By default, the transposition is set to 13 positions, corresponding to a shift of half the alphabet.

The `reflect(char input)` method calculates the reflected character by adding the configured transposition to the incoming character's alphabet index, using modulo 26.

The `sendBack(char input)` method first reflects the incoming character and then passes it to the last rotor, initiating the return journey through the rotor assembly.

The transposition can also be customized through the constructor or `setTransposition()` method.

### EnigmaMachine

The `EnigmaMachine` class coordinates all the individual components and manages the encryption process.

Its constructor accepts two configuration lists:

- `rotorSettings` – Contains the initial position and wiring configuration for each rotor.
- `pbSettings` – Contains the character pairs used to configure the plugboard.

The constructor initializes the plugboard, creates the rotors, and connects them in sequence using their previous and next rotor references.

The reflector is attached to the last rotor in the assembly.

**Encryption process**

The `cipherMessage(String message)` method processes the input message character by character.

For each non-space character, the following operations are performed:

1. The character passes through the plugboard.
2. The first rotor advances its position.
3. The character travels forward through all rotors.
4. The reflector redirects the signal.
5. The character travels backwards through the rotor assembly.
6. The resulting character passes through the plugboard again.
7. The encrypted character is appended to the output.

Spaces are preserved in the output and bypass the encryption process entirely.

**Machine reset**

The `resetMachine()` method restores every rotor to its original position.

The initial rotor positions are stored during construction and used when resetting the machine.

## Signal Processing

The signal follows a specific route through the machine.

```text
Input Character
      |
      v
  Plugboard
      |
      v
  Rotor 1
      |
      v
  Rotor 2
      |
      v
  Rotor N
      |
      v
  Reflector
      |
      v
  Rotor N
      |
      v
  Rotor 2
      |
      v
  Rotor 1
      |
      v
  Plugboard
      |
      v
Output Character
```

Each rotor applies a position-dependent substitution to the incoming character.

The reflector then transforms the character before sending it back through the same rotor assembly in reverse order.

Finally, the plugboard applies the same substitution mapping once more before the resulting character is returned.

Because the rotor positions change during encryption, the substitution applied to a character depends on the previous characters processed by the machine.

## Current Limitations

The implementation currently focuses on the interaction between the core components rather than reproducing every feature of the historical Enigma machine.

Some functionality is not yet implemented:

- Notch-based rotor turnover.
- Historical reflector wiring configurations.
- Input validation for rotor wiring and machine settings.
- A complete user-facing configuration and input interface.

Rotor advancement currently relies on a basic full-rotation mechanism, where the next rotor advances only when the preceding rotor completes a full revolution.

## Future Development

The project is currently focused on implementing and testing the core functionality of the Enigma machine. Future development will expand the application beyond its current testing environment.

Planned improvements include:

- **Terminal Interface (CLI):** Reworking `Main.java` to provide a terminal-based interface where users can configure the machine and supply messages through an external text file.
- **File-based Configuration:** Allowing rotor configurations, plugboard connections, and input messages to be loaded from a structured text file.
- **Graphical User Interface (GUI):** Potentially introducing JavaFX to provide a more user-friendly way of configuring and interacting with the Enigma machine.
- **Historical Accuracy:** Implementing notch-based rotor turnover and historically accurate rotor and reflector configurations.
- **Improved Validation:** Expanding input validation and error handling for machine configurations.

## Disclaimer

This project is an educational implementation intended to explore object-oriented programming, modular system design, and the underlying principles of rotor-based substitution ciphers.

It is not intended for modern secure encryption.
