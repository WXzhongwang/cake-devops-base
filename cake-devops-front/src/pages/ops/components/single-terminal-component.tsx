import React, { useEffect, useRef } from "react";
import { Terminal } from "xterm";
import "xterm/css/xterm.css";

interface TerminalComponentProps {
  wsUrl: string;
}

const TerminalComponent: React.FC<TerminalComponentProps> = ({ wsUrl }) => {
  const terminalRef = useRef(null);
  const socketRef = useRef<WebSocket | null>(null);

  useEffect(() => {
    const term = new Terminal();
    if (terminalRef.current) {
      term.open(terminalRef.current);
    }

    const socket = new WebSocket(wsUrl);
    socketRef.current = socket;

    socket.addEventListener("open", () => {
      // Handle WebSocket connection open
    });

    socket.addEventListener("message", (event) => {
      // Handle messages from WebSocket and write to xterm.js
      term.write(event.data);
    });

    term.onData((data) => {
      // Handle xterm.js input and send to WebSocket
      if (socketRef.current) {
        socketRef.current.send(data);
      }
    });

    return () => {
      // Cleanup when component unmounts
      term.dispose();
      if (socketRef.current) {
        socketRef.current.close();
      }
    };
  }, [wsUrl]);

  return <div ref={terminalRef} />;
};

export default TerminalComponent;
