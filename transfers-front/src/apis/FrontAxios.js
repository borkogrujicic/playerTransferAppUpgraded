import axios from 'axios';

var FrontAxios = axios.create({
  baseURL: 'http://localhost:8080/api',
  /* other custom settings */
});

FrontAxios.interceptors.request.use(
  function presretac(config){
    const jwt = window.localStorage['jwt']
    if(jwt){
      config.headers['Authorization']="Bearer " + jwt
    }
    return config;
  }
);
export default FrontAxios;
