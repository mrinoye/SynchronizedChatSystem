Synchronized Multithreaded Group Chat with Farewell Termination.

Description of the scenario and implementation:
The scenario implements a simple group chat system involving three participants. Each participant takes turns sending messages in a synchronized manner. The code system uses Java threads and synchronization mechanisms to ensure that only one participant can send a message simultaneously. It also ensures that the participants take turns while sending messages. The participants communicate using a shared chat object that provides synchronized methods for sending and receiving messages.

Each participant is assigned a unique ID (1, 2, or 3) to distinguish their messages when the chat starts. The participants take turns sending messages in a synchronized manner. Only the participant whose turn it is can send a message while the other participants wait for their turn. Participants enter the content through the console when they send a message. The chat system displays the message to all participants, indicating the sender's ID and content. If a participant decides to end the chat, they can say "Bye" astheir message. Once a participant says "Bye," they leave the chat, and the chat system updates the state accordingly. However, the chat continues between the remaining participants until they say "Bye." Once all participants have said "Bye," the chat ends.

The implementation uses synchronized methods and wait-notify mechanisms to enforce turn-based communication and ensure proper synchronization between the participants. The chat object keeps track of the current turn, the number of participants left in the chat, and whether participants have said "Bye." Each participant thread synchronizes on the chat object to send messages and waits if it's not their turn. Overall, this synchronized multithreaded chat system allows three participants to engage in a chat, taking turns to send messages while ensuring proper synchronization and termination when all participants have said "Bye."

Threads used in the code:
The code uses three threads to represent the participants in the group chat.
1. thread1 (Participant1)
2. thread2 (Participant2)
3. thread3 (Participant3) 
