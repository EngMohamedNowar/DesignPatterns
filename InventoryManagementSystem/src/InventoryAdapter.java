// InventoryAdapter.java: Adapter for External System
class InventoryAdapter implements ThirdPartyInventory {
    private ExternalInventorySystem externalSystem;

    public InventoryAdapter(ExternalInventorySystem externalSystem) {
        this.externalSystem = externalSystem;
    }

    @Override
    public void syncInventory(String data) {
        externalSystem.uploadData(data);
    }
} // End of InventoryAdapter