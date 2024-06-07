'use strict'

import axios from "axios";

const API = "";

export const api = axios.create({
    baseURL: `${API}`,
    timeout: 1000,
    withCredentials: true,
    headers: {},
});