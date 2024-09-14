import Link from "next/link";
import { UserNav } from "./UserNav";

export function Navbar() {
    return (
        <nav className="w-full border-b">
            <div className="flex items-center justify-between container mx-auto px-5 lg:px-10 py-5">
                <Link href={"/"}>
                    <h1 className="text-xl font-bold text-blue-600 w-32 hidden lg:block">
                        Cook Wongnai
                    </h1>
                </Link>
                <div className="rounded-full border px-5 py-2">
                    <h1>Hello from the search</h1>
                </div>
                <UserNav />
            </div>
        </nav>
    );
}