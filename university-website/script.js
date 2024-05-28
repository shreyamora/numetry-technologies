function sendMessage() {
    const input = document.getElementById('chat-input');
    const message = input.value.trim().toLowerCase();

    if (message) {
        const chatContent = document.getElementById('chat-content');

        // Display user's message
        const userMessage = document.createElement('div');
        userMessage.textContent = `You: ${input.value}`;
        chatContent.appendChild(userMessage);

        // Generate bot response
        const botResponse = getBotResponse(message);
        const botMessage = document.createElement('div');
        botMessage.textContent = `Bot: ${botResponse}`;
        chatContent.appendChild(botMessage);

        // Clear input and scroll to bottom
        input.value = '';
        chatContent.scrollTop = chatContent.scrollHeight;
    }
}

function getBotResponse(message) {
    // Define bot responses
    const responses = {
        "hello": "Hiii, How can i help you?",
        "college times": "Our college operates from 9 AM to 5 PM, Monday to Friday.",
        "fee structure": "The fee structure varies by program. Undergraduate programs are approximately $10,000 per semester, while postgraduate programs are around $15,000 per semester.",
        "university name": "Our university is called Example University.",
        "programs offered": "We offer a range of programs including Computer Science, Business Administration, Electrical Engineering, and more.",
        "admission requirements": "The admission requirements include a completed application form, transcripts, letters of recommendation, and a personal statement.",
        "contact information": "You can contact us at contact@exampleuniversity.edu or call us at (123) 456-7890.",
        "library hours": "The library is open from 8 AM to 10 PM, Monday to Saturday.",
        "sports facilities": "We have various sports facilities including a gym, swimming pool, and basketball courts. They are open from 6 AM to 9 PM."
    };

    // Default response if the question is not recognized
    const defaultResponse = "I'm sorry, I don't understand that question. Please ask about college times, fee structure, programs offered, admission requirements, contact information, library hours, or sports facilities.";

    // Return the appropriate response
    return responses[message] || defaultResponse;
}
