import React, { useState, useEffect } from 'react';
import axios, { AxiosResponse, AxiosError, InternalAxiosRequestConfig } from 'axios';

export const Loading: React.FC = () => {
  const [isLoading, setIsLoading] = useState<boolean>(false);

  useEffect(() => {
    // Add request interceptor
    const requestInterceptor = axios.interceptors.request.use(
      (request: InternalAxiosRequestConfig) => {
        setIsLoading(true);
        return request;
      },
      (error: AxiosError) => {
        setIsLoading(false);
        return Promise.reject(error);
      }
    );

    // Add response interceptor
    const responseInterceptor = axios.interceptors.response.use(
      (response: AxiosResponse) => {
        setIsLoading(false);
        return response;
      },
      (error: AxiosError) => {
        setIsLoading(false);
        return Promise.reject(error);
      }
    );

    // Cleanup interceptors when component unmounts
    return () => {
      axios.interceptors.request.eject(requestInterceptor);
      axios.interceptors.response.eject(responseInterceptor);
    };
  }, []);

  return (
    <div className="loading-overlay">
      <div className="spinner">Loading...</div>
    </div>
  );
};

export default Loading;
