'use strict'

import {useEffect, useState} from "react";
import {api} from "./apis/connections.ts";

export interface UserInterface {
    name: string,
    loginUrl: string,
    logoutUrl: string,
    principal: never | null,
    xsrfToken: string,
}

export function useApp() {
    const [user, setUser] = useState<UserInterface | null>(null)
    useEffect(() => {
        const fetchUser = async () => {
            try {
                const res = await api.get("/user",{
                    withCredentials: true,
                })
                const user = await res.data as UserInterface
                setUser(user)
            } catch (error) {
                setUser(null)
            }
        }
        fetchUser().then()
    }, []);

    return {
        user
    }
}