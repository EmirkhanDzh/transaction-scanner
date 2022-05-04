import axios from "axios";
import { BASE_URL } from "../components/Constants";

export default axios.create({baseURL:BASE_URL})