// src/services/request.ts

import { extend, ResponseError, RequestOptionsInit } from "umi-request";
import { message } from "antd";
import { API } from "typings";
import { history } from "umi";

interface CustomError<T> extends ResponseError {
  data: API.ResponseBody<T>; // Add any additional properties you want to include in the error
}

interface CustomResponse {
  // Define the structure of your custom response data
  data: any;
  success: boolean;
}

const errorHandler = (error: CustomError<any>) => {
  // Add your custom error handling logic here
  const { response, data } = error;
  // debugger;

  if (response.status !== 200 || !data.success) {
    // The request was made and the server responded with a status code
    console.error("Response error:", response.status, data);
    // debugger;
    message.error(data.msg);
  } else {
    // The request was made but no response was received
    console.error("Request error:", data.msg);
  }

  // You can throw an error or return a custom response here
  throw error;
};

const request = extend({
  errorHandler, // Use the custom error handler
  // responseInterceptors: [
  //   async (response: CustomResponse, options: RequestOptionsInit) => {
  //     // Customize your response here
  //     console.log("Custom response interceptor:", response, options);

  //     // Check if the response contains a redirect status code (302)
  //     if (response.status === 302) {
  //       // Extract the redirection URL from the response headers
  //       const redirectUrl = response.headers.get("Location");

  //       // Redirect the user to the specified page
  //       if (redirectUrl) {
  //         history.push(redirectUrl);
  //       } else {
  //         console.error("Invalid redirect URL");
  //       }
  //     } else {
  //       // Handle other responses
  //       // You can also modify the response here if needed
  //       return response;
  //     }
  //   },
  // ],
});

export default request;
