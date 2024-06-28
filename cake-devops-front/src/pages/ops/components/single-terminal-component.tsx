import host, { HostModel, HostTerminalConfig } from "@/models/host";
import React, { useEffect, useRef, useState } from "react";
import { Dispatch, connect } from "umi";
import { Terminal } from "xterm";
import "xterm/css/xterm.css";

interface TerminalComponentProps {
  wsUrl: string;
  dispatch: Dispatch;
  host: HostModel | null;
}

const TerminalComponent: React.FC<TerminalComponentProps> = ({
  wsUrl,
  dispatch,
  host,
}) => {
  const terminalRef = useRef(null);
  const socketRef = useRef<WebSocket | null>(null);
  const [terminalConfig, setTerminalConfig] =
    useState<HostTerminalConfig | null>(null);
  const [supportedPty, setSupportedPty] = useState<string[]>([]);

  useEffect(() => {
    dispatch({
      type: "host/getSupportedPty",
      payload: {},
      callback: (res: string[]) => {
        setSupportedPty(res);
      },
    });
    dispatch({
      type: "host/getTerminalConfig",
      payload: {
        hostId: host?.hostId,
      },
      callback: (res: HostTerminalConfig) => {
        setTerminalConfig(terminalConfig);
      },
    });
  }, []);

  useEffect(() => {
    const term = new Terminal();
    if (terminalRef.current) {
      term.open(terminalRef.current);
    }

    console.log("wsUrl", wsUrl + host?.hostId);
    const socket = new WebSocket(wsUrl + host?.hostId);
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
export default connect(({ host }) => ({}))(TerminalComponent);
