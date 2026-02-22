const DeliveryTracker = require("../src/deliveryTracker.js");
const StatusAggregator = require("../src/statusAggregator.js");

describe("SMS and email delivery status tracker", () => {
    test("should process valid input", () => {
        const obj = new DeliveryTracker();
        expect(obj.process({ key: "val" })).not.toBeNull();
    });
    test("should handle null", () => {
        const obj = new DeliveryTracker();
        expect(obj.process(null)).toBeNull();
    });
    test("should track stats", () => {
        const obj = new DeliveryTracker();
        obj.process({ x: 1 });
        expect(obj.getStats().processed).toBe(1);
    });
    test("support should work", () => {
        const obj = new StatusAggregator();
        expect(obj.process({ data: "test" })).not.toBeNull();
    });
});
