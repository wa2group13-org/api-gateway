import {createContext} from "react";
import {UserInterface} from "../App.hooks.ts";

const UserContext = createContext<UserInterface | null>(null)
export default UserContext