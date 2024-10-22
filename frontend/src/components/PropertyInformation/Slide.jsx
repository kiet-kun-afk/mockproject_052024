import house1 from "../../assets/house1.jpeg";
import house2 from "../../assets/house2.jpeg";
import house3 from "../../assets/house3.webp";
import house4 from "../../assets/house4.jpeg";
import house5 from "../../assets/house5.jpeg";
import house6 from "../../assets/house6.jpeg";
import { Tab } from "@headlessui/react";
const PropertyInfomationSlide = () => {
    const listImage = [house1, house2, house3, house4, house5, house6]
    return (
        <Tab.Group as="div" className={"flex flex-col-reverse"}>
            <div className="mx-auto mt-6 hidden w-full max-w-3xl sm:block lg:max-w-none ">
                <Tab.List className={"grid grid-cols-5 gap-6 border-2 p-3 shadow-xl rounded-2xl"} >
                    {listImage.map((image, index) => (
                        <Tab key={index} className={"relative h-28 w-full flex aspect-square cursor-pointer items-center justify-center rounded-md bg-white"}>
                            {({ selected }) => (
                                <div>
                                    <span className="absolute h-28 w-full aspect-square inset-0 overflow-hidden rounded-md">
                                        <img
                                            src={image}
                                            alt=""
                                            className="object-cover object-center w-full h-full"
                                        />
                                    </span>
                                    <span className={`absolute h-28 inset-0 rounded-md ring-2 ring-offset-2 ${selected ? "ring-black" : "ring-transparent"}`} />
                                </div>
                            )}
                        </Tab>
                    ))}
                </Tab.List>
            </div>
            <Tab.Panels className={"aspect-square w-full h-[500px]"} >
                {listImage.map((image, index) => (
                    <Tab.Panel key={index} >
                        <div className="aspect-square relative h-[500px] w-full sm:rounded-lg overflow-hidden">
                            <img
                                src={image}
                                alt="Image"
                                className="origin-center w-full h-full"
                            />
                        </div>
                    </Tab.Panel>
                ))}
            </Tab.Panels>
        </Tab.Group>
    );
}

export default PropertyInfomationSlide